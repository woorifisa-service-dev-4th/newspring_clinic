<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Veterinarians</title>
                <style>
                    table {border-collapse: collapse; width: 100%;}
                    table, th, td {border: 1px solid black;}
                    th, td {padding: 10px;}
                    th {background-color: #f2f2f2;}
                </style>
            </head>
            <body>
                <h2>Veterinarians</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Specialties</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="//vet">
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td><xsl:value-of select="firstName"/><xsl:text> </xsl:text><xsl:value-of select="lastName"/></td>
                                <td>
                                    <xsl:for-each select="specialties/specialty">
                                        <xsl:value-of select="name"/>
                                        <xsl:text>, </xsl:text>
                                    </xsl:for-each>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>